describe('Discover Page', () => {
  beforeEach(() => {
    // Intercepting the /api/quiz/categories endpoint
    cy.intercept('GET', '/api/quiz/categories', {
      statusCode: 200,
      body: [
        {
          id: 1,
          name: 'General Knowledge'
        },
        {
          id: 2,
          name: 'History'
        },
        {
          id: 3,
          name: 'Science'
        }
      ]
    }).as('getCategories');

  });

  it.skip('should load and display quizzes on page load', () => {

    // Visit the discover page
    cy.visit('/');


    // Intercept API calls to /api/quiz
    cy.intercept('GET', '/api/quiz?*', (req) => {
      req.reply({
        statusCode: 200,
        body: {
          content: [
            { id: 1, title: 'Quiz 1', categoryId: '1', questions: 10, difficulty: 'EASY' },
            { id: 2, title: 'Quiz 2', categoryId: '2', questions: 20, difficulty: 'MEDIUM' },
            { id: 3, title: 'Quiz 3', categoryId: '3', questions: 30, difficulty: 'HARD' }
          ],
          totalPages: 2,
          totalElements: 6,
          first: true,
          last: false,
          number: 0
        }
      });
    }).as('getQuizzes');

    // Wait for the quizzes API response
    cy.wait('@getQuizzes');

    // Check that the correct number of quizzes are displayed
    cy.get('.grid .max-w-sm').should('have.length', 3);

    // Verify the titles of the quizzes
    cy.get('.grid .max-w-sm').eq(0).should('contain', 'Quiz 1');
    cy.get('.grid .max-w-sm').eq(1).should('contain', 'Quiz 2');
    cy.get('.grid .max-w-sm').eq(2).should('contain', 'Quiz 3');

    // Verify the difficulty badges
    cy.get('.grid .max-w-sm').eq(0).should('contain', 'EASY');
    cy.get('.grid .max-w-sm').eq(1).should('contain', 'MEDIUM');
    cy.get('.grid .max-w-sm').eq(2).should('contain', 'HARD');
  });

  it.skip('should search for quizzes by title', () => {
    cy.visit('/');

    // Type in the search input and check that it filters the quizzes
    cy.get('input#search').type('Quiz 1');
    cy.get('button').contains('Search').click();

    // Intercept the search request
    cy.intercept('GET', '/api/quiz*', {
      statusCode: 200,
      body: {
        content: [
          { id: 1, title: 'Quiz 1', categoryId: '1', questions: 10, difficulty: 'EASY' }
        ],
        totalPages: 1,
        totalElements: 1,
        first: true,
        last: true,
        number: 0
      }
    }).as('searchQuizzes');

    // Wait for the search API call
    cy.wait('@searchQuizzes', { timeout: 10000 });

    // Verify that only the filtered quiz is displayed
    cy.get('.grid .max-w-sm').should('have.length', 1);
    cy.get('.grid .max-w-sm').eq(0).should('contain', 'Quiz 1');
  });

  it.skip('should sort quizzes by difficulty', () => {
    cy.visit('/');

    // Open the sort dropdown and click 'Hardest'
    cy.get('#sortDropdownButton').click();
    cy.get('a').contains('Hardest').click();

    // Intercept the sort request
    cy.intercept('GET', '/api/quiz?sort_difficulty=DESC*', {
      statusCode: 200,
      body: {
        content: [
          { id: 3, title: 'Quiz 3', categoryId: '3', questions: 30, difficulty: 'HARD' },
          { id: 2, title: 'Quiz 2', categoryId: '2', questions: 20, difficulty: 'MEDIUM' },
          { id: 1, title: 'Quiz 1', categoryId: '1', questions: 10, difficulty: 'EASY' }
        ],
        totalPages: 1,
        totalElements: 3,
        first: true,
        last: true,
        number: 0
      }
    }).as('sortQuizzes');

    // Wait for the sort API call
    cy.wait('@sortQuizzes');

    // Verify the quizzes are sorted by difficulty
    cy.get('.grid .max-w-sm').eq(0).should('contain', 'HARD');
    cy.get('.grid .max-w-sm').eq(1).should('contain', 'MEDIUM');
    cy.get('.grid .max-w-sm').eq(2).should('contain', 'EASY');
  });

  it.skip('should navigate between pages', () => {
    cy.visit('/');

    // Click the 'Next' page button
    cy.get('a[aria-label="Next"]').click();

    // Intercept the API call for the next page
    cy.intercept('GET', '/api/quiz?*', {
      statusCode: 200,
      body: {
        content: [
          { id: 4, title: 'Quiz 1', categoryId: '4', questions: 10, difficulty: 'MEDIUM' },
          { id: 5, title: 'Quiz 2', categoryId: '5', questions: 15, difficulty: 'EASY' },
          { id: 6, title: 'Quiz 3', categoryId: '6', questions: 25, difficulty: 'HARD' },
          { id: 6, title: 'Quiz 4', categoryId: '6', questions: 25, difficulty: 'HARD' },
          { id: 6, title: 'Quiz 5', categoryId: '6', questions: 25, difficulty: 'HARD' },
          { id: 6, title: 'Quiz 6', categoryId: '6', questions: 25, difficulty: 'HARD' },
          { id: 4, title: 'Quiz 7', categoryId: '4', questions: 10, difficulty: 'MEDIUM' },
          { id: 5, title: 'Quiz 8', categoryId: '5', questions: 15, difficulty: 'EASY' },
          { id: 6, title: 'Quiz 9', categoryId: '6', questions: 25, difficulty: 'HARD' },
          { id: 6, title: 'Quiz 10', categoryId: '6', questions: 25, difficulty: 'HARD' }
        ],
        totalPages: 2,
        totalElements: 6,
        first: false,
        last: true,
        number: 1
      }
    }).as('getNextPageQuizzes');

    // Wait for the next page API call
    cy.get('a').contains('span', 'Next').parents('a').click();

    // Verify that the quizzes on the second page are displayed
    cy.get('.grid .max-w-sm').should('have.length', 4);
    cy.get('.grid .max-w-sm').eq(0).should('contain', 'Quiz 7');
    cy.get('.grid .max-w-sm').eq(1).should('contain', 'Quiz 8');
    cy.get('.grid .max-w-sm').eq(2).should('contain', 'Quiz 9');
    cy.get('.grid .max-w-sm').eq(2).should('contain', 'Quiz 11');
  });

  it('should load quizzes and categories', () => {
    cy.visit('/');

    // Intercept API calls to /api/quiz
    cy.intercept('GET', '/api/quiz?*', (req) => {
      req.reply({
        statusCode: 200,
        body: {
          content: [
            { id: 1, title: 'Quiz 1', categoryId: '1', questions: 10, difficulty: 'EASY' },
            { id: 2, title: 'Quiz 2', categoryId: '2', questions: 20, difficulty: 'MEDIUM' },
            { id: 3, title: 'Quiz 3', categoryId: '3', questions: 30, difficulty: 'HARD' }
          ],
          totalPages: 2,
          totalElements: 6,
          first: true,
          last: false,
          number: 0
        }
      });
    }).as('getQuizzes');

    // Wait for the quizzes to be loaded
    cy.wait('@getQuizzes',{ timeout: 10000 }).its('response.statusCode').should('eq', 200);
    cy.wait('@getCategories', { timeout: 10000 }).its('response.statusCode').should('eq', 200);


    cy.get('select#category')
      .should('be.visible') // Ensure the select element is visible
      .find('option') // Find all option elements within the select
      .should('have.length', 4); // Assert the number of options

    cy.get('select#category')
      .find('option')
   .contains('General Knowledge')
      .should('exist');

    cy.get('select#category')
      .find('option')
      .contains('History')
      .should('exist');

    cy.get('select#category')
      .find('option')
      .contains('Science')
      .should('exist');
  });
});
