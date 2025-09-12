INSERT INTO hotels (id, name)
    VALUES (1, 'Sunprime Ocean View');

INSERT INTO scrape_profiles (id, scrape_url, duration_weeks, adults, children, hotel_id)
    VALUES (1,
            'https://www.tjareborg.fi/kanariansaaret/teneriffa/playa-de-las-americas/sunprime-ocean-view?UseBookingFlow=true&QueryDepID=12728&QueryResID=12190&QueryDepDate=20251221&QueryUnits=0&QueryAges=42%2C42&QueryDur=8&ItemId=89669&qf=flowCharter&selectedTransport=flight%7CCgJURhIkODU4Mjc1YmItNTI1OC00MDE1LWExYjUtYTIwMmI5M2VhODlh&SelectedFlightClass=&RoomKey=hotel%7CCgJURhIDVEZTGgRTVU9WIgZBMTJCQUw&SelectedMeals=#hotel-search-app-root',
            1,
            2,
            0,
            1
            );