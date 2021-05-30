function Alumno(nombre, faltas, notas) {
  this.nombre = nombre;
  this.faltas = faltas;
  this.notas = notas;
  this.calcularPromedio = function () {
    let sumaNotas = 0;
    if (!this.notas.length) return sumaNotas;
    for (let i = 0; i < this.notas.length; i++) {
      sumaNotas += this.notas[i];
    }
    return sumaNotas / this.notas.length;
  };
  this.faltar = function () {
    this.faltas++;
  };
}

module.exports = { Alumno };
