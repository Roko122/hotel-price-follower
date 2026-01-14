import {
  MultiSelect,
  MultiSelectContent,
  MultiSelectGroup,
  MultiSelectItem,
  MultiSelectTrigger,
  MultiSelectValue
} from '@/components/ui/multi-select.js';

import type { JSX } from 'react';
import { formatDateWithWeekdayAndYear } from '@/utils/dateConverter.js';

type DatesPickerProps = {
  dates: string[];
};

function DatesPicker(props: DatesPickerProps): JSX.Element {
  const { dates } = props;

  return (
    <MultiSelect maxSelections={4}>
      <MultiSelectTrigger className="w-full">
        <MultiSelectValue placeholder="Valitse 1-4 lähtöpäivää" />
      </MultiSelectTrigger>
      <MultiSelectContent search={{ placeholder: 'Hae lähtöpäiviä', emptyMessage: 'Lähtöpäiviä ei löytynyt.' }}>
        <MultiSelectGroup>
          {dates?.map((date: string) => (
            <MultiSelectItem key={date} value={date}>
              {formatDateWithWeekdayAndYear(date)}
            </MultiSelectItem>
          ))}
        </MultiSelectGroup>
      </MultiSelectContent>
    </MultiSelect>
  );
}

export default DatesPicker;
