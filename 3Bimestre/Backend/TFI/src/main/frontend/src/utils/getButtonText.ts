import { Direction, Mode } from '@types';

export const getButtonText = (button: Direction, mode: Mode) => {
  if (button === Direction.Left) {
    if (mode === Mode.View) return 'ELIMINAR';
    else if (mode === Mode.Delete) return 'CONFIRMAR ELIMINACIÓN';
    else return 'GUARDAR';
  } else {
    if (mode === Mode.View) return 'EDITAR';
    else return 'CANCELAR';
  }
};
