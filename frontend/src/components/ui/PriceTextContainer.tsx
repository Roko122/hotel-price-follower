import PriceContainer from '@/components/ui/PriceContainer.js';
import { formatDate } from '@/util/dateConverter.js';

import type { JSX } from 'react';
import type { PriceCategory, PriceSummary } from '@/types/Price.js';

type PriceTextContainerProps = {
  category: PriceCategory;
  prices: PriceSummary[];
};

function PriceTextContainer(props: PriceTextContainerProps): JSX.Element {
  const { category, prices } = props;

  const isLatestPrice = category === 'latestPrice';

  const text = (): string => {
    switch (category) {
      case 'latestPrice':
        return 'Viimeisimmät hinnat';
      case 'min30days':
        return '30 päivän alimmat hinnat';
      case 'allTimeMin':
        return 'Alimmat hinnat seuranta-aikana';
      default:
        throw new Error('Unknown category');
    }
  };

  const displayText = (): JSX.Element => {
    if (isLatestPrice && prices.length > 0) {
      const fetchDate: string = prices[0].latestPrice.fetchDate;
      const dateToday: string = new Date().toISOString().split('T')[0];
      const isToday: boolean = dateToday === fetchDate;

      return (
        <div className="flex justify-between items-center w-full">
          <span className="font-bold text-xl">{text()}</span>
          {isToday ? (
            <span className="font-bold">Tänään</span>
          ) : (
            <span className="font-bold">{formatDate(fetchDate)}</span>
          )}
        </div>
      );
    } else {
      return <p className="font-bold text-xl">{text()}</p>;
    }
  };

  return (
    <div className="flex flex-col gap-3 px-10 py-5 items-start border rounded-2xl border-gray-500 shadow-lg inset-shadow-2xs w-full">
      {displayText()}
      <PriceContainer category={category} prices={prices} />
    </div>
  );
}

export default PriceTextContainer;
