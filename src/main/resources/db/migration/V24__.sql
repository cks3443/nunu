ALTER TABLE notice
ALTER
COLUMN text TYPE VARCHAR(1000) USING (text::VARCHAR(1000));