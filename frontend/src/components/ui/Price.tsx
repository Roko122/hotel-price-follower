import { Tooltip, TooltipContent, TooltipTrigger } from '@/components/ui/tooltip';
import { HelpCircle } from 'lucide-react';
import { formatDate } from '@/utils/dateConverter.js';

import type { JSX } from 'react';

type PriceProps = {
  price: number;
  date: string;
  soldOut?: boolean;
  additionalInformation?: string | null;
};

function Price(props: PriceProps): JSX.Element {
  const { price, date, soldOut, additionalInformation } = props;

  const showTooltip: boolean = additionalInformation !== null || soldOut === true;

  const priceToShow = (): string => {
    if (soldOut || price === null) {
      return '-';
    } else {
      return `${price} €`;
    }
  };

  const tooltipContent = (): JSX.Element => {
    if (soldOut) {
      return <p>Loppuunmyyty</p>;
    } else if (additionalInformation !== undefined && additionalInformation !== null) {
      return <p>{additionalInformation.charAt(0).toUpperCase() + additionalInformation.slice(1)}</p>;
    } else if (price === null) {
      return <p>Ei hinta tietoja</p>;
    } else {
      return <p>Hinta {formatDate(date)}</p>;
    }
  };

  return (
    <div className="flex flex-col justify-center items-center">
      <p className="font-bold text-2xl text-center min-w-20">{priceToShow()}</p>
      {showTooltip && (
        <Tooltip>
          <TooltipTrigger asChild>
            <HelpCircle className="w-3.5 h-3.5 text-gray-500 hover:text-gray-600 cursor-help" />
          </TooltipTrigger>
          <TooltipContent side="bottom">{tooltipContent()}</TooltipContent>
        </Tooltip>
      )}
    </div>
  );
}

export default Price;
