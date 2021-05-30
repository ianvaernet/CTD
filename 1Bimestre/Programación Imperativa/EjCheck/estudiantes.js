const { Alumno } = require('./alumno');
const listaDeEstudiantes = [
  new Alumno('Ian Vaernet', 1, [8, 9, 10, 8]),
  new Alumno('Liam Izaguirre', 4, [8, 8, 7, 7]),
  new Alumno('Sofia Perez Zavala', 2, [7, 6, 7, 7]),
  new Alumno('Lucas Carranza', 2, [8, 8, 7, 9]),
];

module.exports = { listaDeEstudiantes };
