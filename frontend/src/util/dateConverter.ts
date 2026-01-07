export const formatDate = (date: string): string => {
  const [year, month, day] = date.split('-');
  return `${day}.${month}.${year}`;
};

export const formatDateWithWeekday = (date: string): string => {
  const weekdays: string[] = ['Su', 'Ma', 'Ti', 'Ke', 'To', 'Pe', 'La'];
  const weekday: string = weekdays[new Date(date).getDay()];

  const [_year, month, day] = date.split('-');
  return `${weekday} ${day}.${month}.`;
};
