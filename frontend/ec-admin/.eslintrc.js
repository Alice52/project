// https://eslint.org/docs/user-guide/configuring

module.exports = {
  root: true,
  parser: 'babel-eslint',
  parserOptions: {
    sourceType: 'module'
  },
  env: {
    browser: true,
  },
  // https://github.com/standard/standard/blob/master/docs/RULES-en.md
  extends: 'standard',
  // required to lint *.vue files
  plugins: [
    'html'
  ],
  // add your custom rules here
  rules: {
    // allow async-await
    semi: 0,
    quotes: [0, 'single'],
    eqeqeq: 0,
    'comma-dangle': 0,
    'spaced-comment': 0,
    'space-before-function-paren': 0,
    'space-before-blocks': 0,
    'no-irregular-whitespace': 0,
    'space-infix-ops': 0,
    'generator-star-spacing': 'off',
    // allow debugger during development
    'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'off'
  }
}
