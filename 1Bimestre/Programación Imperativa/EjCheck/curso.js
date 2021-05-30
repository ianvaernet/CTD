const { Alumno } = require('./alumno');
const { listaDeEstudiantes } = require('./estudiantes');

const curso = {
  nombre: 'Programación Imperativa',
  notaDeAprobacion: 7,
  faltasMaximas: 3,
  listaDeEstudiantes,
  agregarAlumnos: function (...alumnos) {
    this.listaDeEstudiantes.push(...alumnos);
  },
  aprobadoPor: function (alumno) {
    if (alumno.calcularPromedio() > this.notaDeAprobacion && alumno.faltas < this.faltasMaximas) {
      return true;
    }
    if (alumno.faltas === this.faltasMaximas && alumno.calcularPromedio() >= this.notaDeAprobacion * 1.1) {
      return true;
    }
    return false;
  },
  condicionEstudiantes: function () {
    const condiciones = [];
    for (let i = 0; i < this.listaDeEstudiantes.length; i++) {
      condiciones.push(this.aprobadoPor(this.listaDeEstudiantes[i]));
    }
    return condiciones;
  },
};

curso.agregarAlumnos(new Alumno('Nayla Saez', 3, [7, 7, 7, 7]), new Alumno('Julieta Capone', 3, [7, 8, 7, 9]));
console.log(curso.condicionEstudiantes());
