import { Mode } from '@types';

export const changeMode = (mode: Mode, setMode: (mode: Mode) => void, reset?: () => void) => {
  if (mode === Mode.View) setMode(Mode.Edit);
  else {
    if (reset) reset();
    setMode(Mode.View);
  }
};
