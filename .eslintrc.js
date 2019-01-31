module.exports = {
    root: true,
    parser: 'babel-eslint',
    parserOptions: {
        sourceType: 'module'
    },
    env: {
        browser: true,
        node: true,
        es6: true,
    },
    extends: 'eslint:recommended',
    // required to lint *.vue files
    plugins: [
        'html'
    ],
    rules: {
        'indent': ['error', 4, {'SwitchCase': 1}],
        'no-console': 0, // 0 = off, 1 = warn, 2 = error
        'no-control-regex': 0, // 0 = off, 1 = warn, 2 = error
        'no-empty': 2,
        'no-eq-null': 2,
        'no-fallthrough': 0,
        'no-new': 0,
        'no-unreachable': 0,
        'no-unused-vars': 0, // 0 = off, 1 = warn, 2 = error
        'no-useless-escape': 0, // 0 = off, 1 = warn, 2 = error
        'quotes': ['error', 'single'],
        'semi': ['error', 'always']
    }
};

