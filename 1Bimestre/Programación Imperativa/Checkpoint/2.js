const autos = require('./autos');

let concesionaria = {
  autos: autos,
  buscarAuto: function (patente) {
    for (let i = 0; i < this.autos.length; i++) {
      if (this.autos[i].patente === patente) return this.autos[i];
    }
    return null;
  },
};
