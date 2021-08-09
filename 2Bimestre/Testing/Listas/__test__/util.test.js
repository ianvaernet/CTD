const { generateText, createElement, validateInput } = require('../util');

// describe('pruebas de generateText', () => {
//   test('happy path', () => {
//     const result = generateText('un nombre', 20);
//     expect(result).toBe('un nombre (20 years old)');
//   });

//   test('edad nula', () => {
//     const result = generateText('un nombre', null);
//     expect(result).toBe('');
//   });
// });

// describe('pruebas de createElement', () => {
//   test('Create new element with data', () => {
//     const element = createElement('li', 'example element', 'user-item');
//     expect(element.textContent).toBe('example element');
//   });
// });

//   describe('pruebas de validateInput', () => {
//     test('Create new element with data', () => {
//       const element = createElement('li', 'example element', 'user-item');
//       expect(element.textContent).toBe('example element');
//     });
//   });

describe('Pruebas de salida de datos', () => {
  test('Salida con datos', () => {
    const text = generateText('Daniel', 30);
    expect(text).toBe('Daniel (30 years old)');
  });

  test('Salida con datos vacios', () => {
    const text = generateText('', null);
    expect(text).toBe(' (null years old)');
  });

  test('Salida sin datos', () => {
    const text = generateText();
    expect(text).toBe('undefined (undefined years old)');
  });
});
