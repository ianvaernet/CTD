const autos = require('./autos');

let concesionaria = {
  autos: autos,
  buscarAuto: function (patente) {
    for (let i = 0; i < this.autos.length; i++) {
      if (this.autos[i].patente === patente) return this.autos[i];
    }
    return null;
  },
  venderAuto: function (patente) {
    const auto = this.buscarAuto(patente);
    if (auto) auto.vendido = true;
  },
  autosParaLaVenta: function () {
    const autos = [];
    for (let i = 0; i < this.autos.length; i++) {
      if (!this.autos[i].vendido) autos.push(this.autos[i]);
    }
    return autos;
  },
  autosNuevos: function () {
    const autos = this.autosParaLaVenta();
    const autosNuevos = [];
    for (let i = 0; i < autos.length; i++) {
      if (autos[i].km < 100) autosNuevos.push(autos[i]);
    }
    return autosNuevos;
  },
  listaDeVentas: function () {
    const ventas = [];
    for (let i = 0; i < this.autos.length; i++) {
      if (this.autos[i].vendido) ventas.push(this.autos[i].precio);
    }
    return ventas;
  },
};
