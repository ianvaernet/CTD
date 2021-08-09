const { init, limpiar, resolver, resetear, getVariables } = require('../funcionalidad');
const fs = require('fs');

window.document.body.innerHTML = fs.readFileSync('calculadora.html');

describe('prueba init', () => {
  init();
  test('botón cero', () => {
    resultado.textContent = '';
    cero.click();
    expect(resultado.textContent).toBe('0');
  });
  test('botón uno', () => {
    resultado.textContent = '';
    uno.click();
    expect(resultado.textContent).toBe('1');
  });
  test('botón dos', () => {
    resultado.textContent = '';
    dos.click();
    expect(resultado.textContent).toBe('2');
  });
  test('botón tres', () => {
    resultado.textContent = '';
    tres.click();
    expect(resultado.textContent).toBe('3');
  });
  test('botón cuatro', () => {
    resultado.textContent = '';
    cuatro.click();
    expect(resultado.textContent).toBe('4');
  });
  test('botón cinco', () => {
    resultado.textContent = '';
    cinco.click();
    expect(resultado.textContent).toBe('5');
  });
  test('botón seis', () => {
    resultado.textContent = '';
    seis.click();
    expect(resultado.textContent).toBe('6');
  });
  test('botón siete', () => {
    resultado.textContent = '';
    siete.click();
    expect(resultado.textContent).toBe('7');
  });
  test('botón dos', () => {
    resultado.textContent = '';
    ocho.click();
    expect(resultado.textContent).toBe('8');
  });
  test('botón dos', () => {
    resultado.textContent = '';
    nueve.click();
    expect(resultado.textContent).toBe('9');
  });
  test('botón +', () => {
    resultado.textContent = '';
    cero.click();
    uno.click();
    suma.click();
    expect(getVariables().operandoa).toBe('01');
    expect(getVariables().operacion).toBe('+');
  });
  test('botón -', () => {
    resultado.textContent = '';
    dos.click();
    tres.click();
    resta.click();
    expect(getVariables().operandoa).toBe('23');
    expect(getVariables().operacion).toBe('-');
  });
  test('botón *', () => {
    resultado.textContent = '';
    cuatro.click();
    cinco.click();
    multiplicacion.click();
    expect(getVariables().operandoa).toBe('45');
    expect(getVariables().operacion).toBe('*');
  });
  test('botón /', () => {
    resultado.textContent = '';
    seis.click();
    siete.click();
    division.click();
    expect(getVariables().operandoa).toBe('67');
    expect(getVariables().operacion).toBe('/');
  });
  test('botón C', () => {
    resultado.textContent = '';
    ocho.click();
    resta.click();
    nueve.click();
    reset.click();
    expect(getVariables().operandoa).toBe(0);
    expect(getVariables().operacion).toBe('');
    expect(getVariables().operandob).toBe(0);
  });
});

describe('prueba resetear', () => {
  test('función resetear', () => {
    cinco.click();
    suma.click();
    dos.click();
    resetear();
    expect(resultado.textContent).toBe('');
    expect(getVariables().operandoa).toBe(0);
  });
});

describe('prueba limpiar', () => {
  test('función limpiar', () => {
    cinco.click();
    suma.click();
    dos.click();
    limpiar();
    expect(resultado.textContent).toBe('');
    expect(getVariables().operandoa).toBe('5');
  });
});

describe('prueba operaciones', () => {
  test('operación +', () => {
    resultado.textContent = '';
    uno.click();
    suma.click();
    dos.click();
    igual.click();
    expect(resultado.textContent).toBe('3');
  });
  test('operación -', () => {
    resultado.textContent = '';
    tres.click();
    resta.click();
    cuatro.click();
    igual.click();
    expect(resultado.textContent).toBe('-1');
  });
  test('operación *', () => {
    resultado.textContent = '';
    cinco.click();
    multiplicacion.click();
    seis.click();
    igual.click();
    expect(resultado.textContent).toBe('30');
  });
  test('operación /', () => {
    resultado.textContent = '';
    siete.click();
    division.click();
    ocho.click();
    igual.click();
    expect(resultado.textContent).toBe('0.875');
  });
});
