ALTER TABLE hotels MODIFY COLUMN description varchar(255) DEFAULT 'Travel with Peace of Mind';
UPDATE hotels
SET description = 'Travel with Peace of Mind'
WHERE description is null;