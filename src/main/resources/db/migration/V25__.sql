ALTER TABLE sub
ALTER
COLUMN textarea TYPE VARCHAR(10000) USING (textarea::VARCHAR(10000));