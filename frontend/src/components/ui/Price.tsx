import { Tooltip, TooltipContent, TooltipTrigger } from '@/components/ui/tooltip';
import { HelpCircle } from 'lucide-react';

import type { JSX } from 'react';

type PriceProps = {
  price: number;
  date: string;
};

function Price(props: PriceProps): JSX.Element {
  const { price, date } = props;

  return (
    <div className="flex flex-col items-center">
      <p className="font-bold text-2xl text-center pt-2 leading-none">{price} €</p>
      <Tooltip>
        <TooltipTrigger asChild>
          <HelpCircle className="w-4 h-4 text-gray-500" />
        </TooltipTrigger>
        <TooltipContent side="bottom">
          <p>Hinta {date}</p>
        </TooltipContent>
      </Tooltip>
    </div>
  );
}

export default Price;
