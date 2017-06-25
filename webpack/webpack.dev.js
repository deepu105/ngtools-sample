const webpack = require('webpack');
const writeFilePlugin = require('write-file-webpack-plugin');
const webpackMerge = require('webpack-merge');
const BrowserSyncPlugin = require('browser-sync-webpack-plugin');
const ExtractTextPlugin = require("extract-text-webpack-plugin");
const AddAssetHtmlPlugin = require('add-asset-html-webpack-plugin');
const execSync = require('child_process').execSync;
const fs = require('fs');
const path = require('path');

const utils = require('./utils.js');
const commonConfig = require('./webpack.common.js');

const ddlPath = 'target/www/vendor.json';
const ENV = 'dev';

if (!fs.existsSync(utils.root(ddlPath))) {
    execSync('webpack --config webpack/webpack.vendor.js');
}

module.exports = webpackMerge(commonConfig({ env: ENV }), {
    devtool: 'inline-source-map',
    devServer: {
        contentBase: './target/www',
        proxy: [{
            context: [
                /* jhipster-needle-add-entity-to-webpack - JHipster will add entity api paths here */
                '/api',
                '/management',
                '/swagger-resources',
                '/v2/api-docs',
                '/h2-console'
            ],
            target: 'http://127.0.0.1:8080',
            secure: false
        },{
            context: [
                '/websocket'
            ],
            target: 'ws://127.0.0.1:8080',
            ws: true
        }]
    },
    entry: {
        'polyfills': './src/main/webapp/app/polyfills',
        'global': './src/main/webapp/content/scss/global.scss',
        'main': './src/main/webapp/app/app.main'
    },
    output: {
        path: utils.root('target/www'),
        filename: 'app/[name].bundle.js',
        chunkFilename: 'app/[id].chunk.js'
    },
    module: {
        rules: [{
            test: /\.ts$/,
            enforce: 'pre',
            loaders: 'tslint-loader',
            exclude: ['node_modules', new RegExp('reflect-metadata\\' + path.sep + 'Reflect\\.ts')]
        },
        {
            test: /\.ts$/,
            loaders: [
                'angular2-template-loader',
                'awesome-typescript-loader'
            ],
            exclude: ['node_modules/generator-jhipster']
        }]
    },
    plugins: [
        new webpack.optimize.CommonsChunkPlugin({
            names: ['manifest', 'polyfills'].reverse()
        }),
        new BrowserSyncPlugin({
            host: 'localhost',
            port: 9000,
            proxy: {
                target: 'http://localhost:9060',
                ws: true
            }
        }, {
            reload: false
        }),
        new webpack.DllReferencePlugin({
            context: './',
            manifest: require(utils.root(ddlPath))
        }),
        new AddAssetHtmlPlugin([
            { filepath: utils.root('target/www/vendor.dll.js'), includeSourcemap: false }
        ]),
        new ExtractTextPlugin('styles.css'),
        new webpack.NoEmitOnErrorsPlugin(),
        new webpack.NamedModulesPlugin(),
        new writeFilePlugin(),
        new webpack.WatchIgnorePlugin([
            utils.root('src/test'),
        ])
    ]
});
