CREATE TABLE flashcards (
    id UUID PRIMARY KEY,
    question TEXT NOT NULL,
    answer TEXT NOT NULL,
    grade VARCHAR(30) NOT NULL,
    next_recall TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    collection_id UUID NOT NULL,
    CONSTRAINT fk_collection FOREIGN KEY (collection_id) REFERENCES card_collections(id)
);