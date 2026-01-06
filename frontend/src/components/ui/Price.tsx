import { Tooltip, TooltipContent, TooltipTrigger } from '@/components/ui/tooltip';
import { HelpCircle } from 'lucide-react';

import type { JSX } from 'react';

type PriceProps = {
  price: number;
  date: string;
  soldOut?: boolean;
  additionalInformation?: string | null;
};

function Price(props: PriceProps): JSX.Element {
  const { price, date, soldOut, additionalInformation } = props;

  const showTooltip: boolean = additionalInformation !== null;
  const isAdditionalInformationPresent: boolean = additionalInformation === undefined;

  const formatDate = (date: string): string => {
    const [year, month, day] = date.split('-');
    return `${day}.${month}.${year}`;
  };

  return (
    <div className="flex flex-col justify-center items-center">
      <p className="font-bold text-2xl text-center leading-none">{soldOut ? 'Loppuunmyyty' : `${price} €`}</p>
      {showTooltip && (
        <Tooltip>
          <TooltipTrigger asChild>
            <HelpCircle className="w-3.5 h-3.5 text-gray-500 hover:text-gray-600 cursor-help" />
          </TooltipTrigger>
          <TooltipContent side="bottom">
            {isAdditionalInformationPresent ? <p>Hinta {formatDate(date)}</p> : <p>{additionalInformation}</p>}
          </TooltipContent>
        </Tooltip>
      )}
    </div>
  );
}

export default Price;
