ALTER TABLE locations ADD COLUMN zip varchar(255) DEFAULT 0;
ALTER TABLE hotels ADD COLUMN description varchar(1024) DEFAULT null;