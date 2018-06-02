'use strict';

var entries = {
  app: {
    template: 'index.html',
    js: './src/main.js',
  },
  open: {
    template: 'open.html',
    js: './src/open.js',
  },
}
var path = require('path');
var webpack = require('webpack');

var thirdParties = [
  'vue', 'vue-router',

];

var HtmlWebpackPlugin = require('html-webpack-plugin');
var CopyWebpackPlugin = require('copy-webpack-plugin');


const CleanWebpackPlugin = require('clean-webpack-plugin');


var isDevServer = path.basename(require.main.filename) === 'webpack-dev-server.js';


var BUILD_DIR = path.resolve(__dirname,
  '../static');


// the path(s) that should be cleaned
let pathsToClean = [
  'public',
  'favicon.ico'
]

for (var name in entries) {
  var e = entries[name];
  pathsToClean.push(e.template);
}

// the clean options to use
let cleanOptions = {
  root: BUILD_DIR,
  exclude: [],
  verbose: true,
  dry: false
}


var clean = new CleanWebpackPlugin(pathsToClean, cleanOptions);
var Uglify = new webpack.optimize.UglifyJsPlugin({
  sourceMap: true,
  compress: {
    warnings: false
  }
});
var definePlugin = new webpack.DefinePlugin({
  'process.env': {
    NODE_ENV: '"production"'
  }
});
var loadOptions = new webpack.LoaderOptionsPlugin({
  minimize: true
});
var plugins = [
  new webpack.optimize.CommonsChunkPlugin({
    names: 'vendors',
    filename: 'public/common.js',
    hash: isDevServer ? false : true,
    minChunks: Infinity
  }),
  new HtmlWebpackPlugin({
    chunks: ['app', 'vendors'],
    hash: isDevServer ? false : true,
    template: 'template/index.html',
    filename: 'index.html'
  }),
  // new HtmlWebpackPlugin({
  //   chunks: ['admin','vendors'],
  //   template: 'template/index.html',
  //   filename: 'admin.html'
  // }),
  new CopyWebpackPlugin(['template/favicon.ico', {
    from: 'template/lib',
    to: 'public/lib'
  }], {})
];

for (var name in entries) {
  var e = entries[name];
  var plugin = new HtmlWebpackPlugin({
    chunks: [name, 'vendors'],
    hash: isDevServer ? false : true,
    template: 'template/'+e.template,
    filename: e.template
  });
  plugins.push(plugin);
}
if (!isDevServer) {
  plugins.push(definePlugin);
  plugins.push(Uglify);
  plugins.push(loadOptions);
  plugins.push(clean);
}

var buildEntry={
  vendors: thirdParties,
}
for (var name in entries) {
  var e = entries[name];
  buildEntry[name]=e.js;
}
module.exports = {
  entry: buildEntry,
  output: {
    path: BUILD_DIR,
    filename: 'public/js/[name].js'
  },
  resolve: {
    extensions: ['.js', '.jsx'],
    alias: {
      'vue': 'vue/dist/vue.js',
      'jquery': 'jquery/dist/jquery.min.js',
    }
  },
  devServer: {
    proxy: {
      "/api": "http://localhost:8080",
      "/spring": "http://localhost:8080"
    },
    // options:{
    //     historyApiFallback: true
    // }
    // historyApiFallback: true
  },
  devtool: '#eval-source-map',
  module: {
    loaders: [{
      test: /\.css$/, // Only .css files
      loader: 'style-loader!css-loader' // Run both loaders
    }, {
      test: /\.(svg)$/i,
      loader: "file-loader?name=public/svg/[name].[ext]"
    }, {
      test: /\.(jpe?g|png|gif)$/i,
      loader: "file-loader?name=public/images/[name].[ext]"
    }, {
      test: /\.(eot|ttf|woff|woff2)$/,
      loader: 'file-loader?name=public/fonts/[name].[ext]'
    }, {
      test: /\.scss$/,
      include: path.appSrc,
      loaders: ["style-loader", "css-loader", "sass-loader"]
    }, {
      test: /\.vue$/,
      loader: 'vue-loader',
      options: {
        loaders: {
          // Since sass-loader (weirdly) has SCSS as its default parse mode, we map
          // the "scss" and "sass" values for the lang attribute to the right configs here.
          // other preprocessors should work out of the box, no loader config like this necessary.
          'scss': [
            'vue-style-loader',
            'css-loader',
            'sass-loader'
          ],
          'sass': [
            'vue-style-loader',
            'css-loader',
            'sass-loader?indentedSyntax'
          ]
        }
        // other vue-loader options go here
      }
    }],
  },
  plugins: plugins
};
