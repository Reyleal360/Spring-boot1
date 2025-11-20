CREATE INDEX idx_venues_city ON venues(city);
CREATE INDEX idx_venues_country ON venues(country);
CREATE INDEX idx_venues_status ON venues(status);
CREATE INDEX idx_venues_capacity ON venues(capacity);
CREATE INDEX idx_venues_city_country ON venues(city, country);
