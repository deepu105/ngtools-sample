const webpack = require('webpack');
const webpackMerge = require('webpack-merge');
const CopyWebpackPlugin = require('copy-webpack-plugin');
const ExtractTextPlugin = require("extract-text-webpack-plugin");
const Visualizer = require('webpack-visualizer-plugin');
const AotPlugin = require('@ngtools/webpack').AotPlugin;

const utils = require('./utils.js');
const commonConfig = require('./webpack.common.js');

const ENV = 'prod';

module.exports = webpackMerge(commonConfig({ env: ENV }), {
    devtool: 'source-map',
    entry: {
        'polyfills': './src/main/webapp/app/polyfills',
        'vendor': './src/main/webapp/app/vendor-aot',
        'main': './src/main/webapp/app/app.main-aot'
    },
    output: {
        path: utils.root('target/www'),
        filename: 'app/[hash].[name].bundle.js',
        chunkFilename: 'app/[hash].[id].chunk.js'
    },
    module: {
        rules: [{
            test: /\.ts$/,
            loader: '@ngtools/webpack'
        }]
    },
    plugins: [
        new webpack.optimize.CommonsChunkPlugin({
            name: ['main', 'vendor', 'polyfills']
        }),
        new ExtractTextPlugin('[hash].styles.css'),
        new Visualizer({
            // Webpack statistics in target folder
            filename: '../stats.html'
        }),
        // AOT Plugin
        new AotPlugin({
            tsConfigPath: './tsconfig-aot.json',
            entryModule: utils.root('src/main/webapp/app/app.module#Jh4TestAppModule')
        }),
        new webpack.optimize.UglifyJsPlugin({
            beautify: false,
            comments: false,
            sourceMap: true,
            compress: {
                screw_ie8: true,
                warnings: false
            },
            mangle: {
                keep_fnames: true,
                screw_i8: true
            }
        })
    ]
});
