import { ButtonType, Direction, Mode } from '@types';

export const getButtonType = (button: Direction, mode: Mode): ButtonType => {
  if (button === Direction.Left) {
    if (mode === Mode.View || mode === Mode.Delete) return ButtonType.Danger;
    else return ButtonType.Success;
  } else {
    if (mode === Mode.View) return ButtonType.Warning;
    else return ButtonType.Secondary;
  }
};
