type PriceSummaryData = {
  price: number;
  soldOut: boolean;
  additionalInfo: string | null;
  fetchDate: string;
};

export type PriceSummary = {
  departureDate: string;
  latestPrice: PriceSummaryData;
  min30days: PriceSummaryData;
  allTimeMin: PriceSummaryData;
};

export type PriceCategory = 'latestPrice' | 'min30days' | 'allTimeMin';

export type PriceData = {
  price: number;
  fetchTime: string;
};
