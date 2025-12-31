type PriceSummaryData = {
  price: number;
  soldOut: boolean;
  additionalInformation: string;
  fetchDate: string;
};

export type PriceData = {
  price: number;
  fetchTime: string;
};

export type PriceSummary = {
  departureDate: string;
  latestPrice: PriceSummaryData;
  min30days: PriceSummaryData;
  allTimeMin: PriceSummaryData;
};
