CREATE TABLE authors (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    age INTEGER NOT NULL,
    country TEXT NOT NULL
);

CREATE TABLE books (
    isbn TEXT NOT NULL,
    title TEXT NOT NULL,
    genre TEXT NOT NULL,
    publisher TEXT NOT NULL,
    year INTEGER NOT NULL,
    author_id INTEGER NOT NULL,
    PRIMARY KEY (isbn),
    FOREIGN KEY (author_id) REFERENCES authors(id)
);
