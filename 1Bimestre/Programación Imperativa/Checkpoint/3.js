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
};
