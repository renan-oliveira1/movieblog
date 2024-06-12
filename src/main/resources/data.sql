INSERT INTO TB_CATEGORY (name, description)
VALUES
    ('HORROR', 'Some Description'),
    ('ACTION', 'Some Description'),
    ('DRAMA', 'Some Description'),
    ('COMEDY', 'Some Description'),
    ('SCI-FI', 'Some Description'),
    ('ROMANCE', 'Some Description'),
    ('THRILLER', 'Some Description'),
    ('FANTASY', 'Some Description'),
    ('MYSTERY', 'Some Description'),
    ('ADVENTURE', 'Some Description'),
    ('DOCUMENTARY', 'Some Description'),
    ('ANIMATION', 'Some Description'),
    ('BIOGRAPHY', 'Some Description'),
    ('CRIME', 'Some Description'),
    ('MUSICAL', 'Some Description');

INSERT INTO TB_MOVIES (movie_id, title, description, premiere, posted)
VALUES
    ('c1fc8563-485b-4702-8565-c634c22ea5ce', 'A Quiet Place', 'In a post-apocalyptic world, a family is forced to live in silence while hiding from monsters with ultra-sensitive hearing.', '2018-04-06', '2018-04-07'),
    ('c4fc8463-423b-5655-8322-c634c22ea5ce', 'Mad Max: Fury Road', 'In a post-apocalyptic wasteland, Max teams up with Furiosa to escape a warlord and his army.', '2015-05-15', '2015-05-16'),
    ('c1fc8563-485b-2323-1162-c634c22ea5ce', 'The Pursuit of Happyness', 'A struggling salesman takes custody of his son as he is poised to begin a life-changing professional endeavor.', '2006-12-15', '2006-12-16');

INSERT INTO TB_MOVIES_CATEGORIES(CATEGORIES_CATEGORY_ID, MOVIE_MODEL_MOVIE_ID )
VALUES
    (1, 'c1fc8563-485b-4702-8565-c634c22ea5ce'),
    (2, 'c1fc8563-485b-4702-8565-c634c22ea5ce'),
    (5, 'c4fc8463-423b-5655-8322-c634c22ea5ce'),
    (2, 'c4fc8463-423b-5655-8322-c634c22ea5ce');

