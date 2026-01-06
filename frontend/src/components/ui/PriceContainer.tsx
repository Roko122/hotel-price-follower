import { Fragment } from 'react';
import Price from '@/components/ui/Price.js';
import VerticalDivider from '@/components/ui/VerticalDivider.js';

import type { JSX } from 'react';
import type { PriceCategory, PriceSummary } from '@/types/Price';

type PriceContainerProps = {
  category: PriceCategory;
  prices: PriceSummary[];
};

function PriceContainer(props: PriceContainerProps): JSX.Element {
  const { category, prices } = props;

  return (
    <div className="flex gap-5 min-h-12">
      {prices.map((priceSummary, index) => {
        const priceData = priceSummary[category];
        return (
          <Fragment key={priceSummary.departureDate}>
            {category === 'latestPrice' ? (
              <Price
                price={priceData.price}
                date={priceData.fetchDate}
                soldOut={priceData.soldOut}
                additionalInformation={priceData.additionalInfo}
              />
            ) : (
              <Price price={priceData.price} date={priceData.fetchDate} />
            )}
            {index < prices.length - 1 && <VerticalDivider />}
          </Fragment>
        );
      })}
    </div>
  );
}

export default PriceContainer;
