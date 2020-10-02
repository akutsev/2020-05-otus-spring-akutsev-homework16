insert into genres (id, genre_name) values (2, 'Drama'), (4, 'Thriller'), (6, 'Adventures'), (8, 'Historical');
insert into authors (id, name) values (1, 'Lev Tolstoy'), (3, 'Mark Twen'), (5, 'Daria Dontsova');
insert into books (id, name, author_id, genre_id) values (1, 'War and Piece', 1, 2), (2, 'Tom Soyer', 3, 6);
insert into comments (id, text, book_id) values (1, 'Book that you will never forget, 5 stars!', 1),
    (2, 'Honestly I did not like that, 3 stars', 1), (3, 'Be sure you will like it!', 2),
    (4, 'Really nice book about friendship', 2);