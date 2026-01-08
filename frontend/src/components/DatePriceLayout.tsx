import type { JSX } from 'react';
import type { PriceSummary } from '@/types/Price.js';
import DateContainer from '@/components/ui/DateContainer.js';
import PriceTextContainer from '@/components/ui/PriceTextContainer.js';

type DatePriceLayoutProps = {
  priceData: PriceSummary[];
};

function DatePriceLayout(props: DatePriceLayoutProps): JSX.Element {
  const { priceData } = props;

  return (
    <>
      <DateContainer dates={priceData} />
      <div className="flex flex-col gap-5">
        <PriceTextContainer prices={priceData} category="latestPrice" />
        <PriceTextContainer prices={priceData} category="min30days" />
        <PriceTextContainer prices={priceData} category="allTimeMin" />
      </div>
    </>
  );
}

export default DatePriceLayout;
