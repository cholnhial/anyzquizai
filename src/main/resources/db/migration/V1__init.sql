CREATE TABLE category (
                          id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(255) NOT NULL
);

CREATE TABLE quiz (
                      id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
                      unique_code VARCHAR(255) UNIQUE NOT NULL,
                      category_id BIGINT(20),
                      title VARCHAR(255) NOT NULL,
                      total_questions INT,
                      photo_path VARCHAR(255),
                      difficulty ENUM('EASY', 'MEDIUM', 'HARD') NOT NULL, -- Specify the possible enum values
                      created DATETIME,
                      INDEX idx_category_id (category_id),
                      INDEX idx_total_questions (total_questions),
                      FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE TABLE question (
                          id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
                          quiz_id BIGINT(20),
                          title VARCHAR(255) NOT NULL,
                          correct_answer_letter VARCHAR(1) NOT NULL,
                          INDEX idx_quiz_id (quiz_id),
                          FOREIGN KEY (quiz_id) REFERENCES quiz(id)
);

CREATE TABLE answer (
                        id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
                        question_id BIGINT(20),
                        title VARCHAR(255) NOT NULL,
                        letter VARCHAR(1) UNIQUE NOT NULL,
                        INDEX idx_question_id (question_id),
                        FOREIGN KEY (question_id) REFERENCES question(id)
);

CREATE TABLE score (
                       id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
                       quiz_id BIGINT(20),
                       country_code VARCHAR(255) NOT NULL,
                       nick_name VARCHAR(255) UNIQUE NOT NULL,
                       total_correct INT,
                       score DOUBLE,
                       INDEX idx_quiz_id (quiz_id),
                       FOREIGN KEY (quiz_id) REFERENCES quiz(id)
);
