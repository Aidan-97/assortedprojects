import React from 'react';
import './App.css';
import coasters from './data/coasters';
import CoasterCard from './components/CoasterCard';

function App() {
  return (
    <div className="App">
      <div className="container">
        <header className="blog-header py-3">
          <div className="row flex-nowrap justify-content-between align-items-center">
            <div className="col-4 pt-1">
              <a className="link-secondary" href="#">
                Subscribe
              </a>
            </div>
            <div className="col-4 text-center">
              <a className="blog-header-logo text-dark" href="#">
                Blackpool Coasters
              </a>
            </div>
            <div className="col-4 d-flex justify-content-end align-items-center">
              <a className="btn btn-sm btn-outline-secondary" href="#">
                Sign up
              </a>
            </div>
          </div>
        </header>

        <main className="row my-4">
          <div className="col-md-16">
            <div className="row row-cols-1 row-cols-md-2 g-4">
              {coasters.map((c) => (
                <CoasterCard key={c.name} coaster={c} />
              ))}
            </div>
          </div>
        </main>

        <footer className="blog-footer">
          <p>
            Blog template built for{' '}
            <a href="https://getbootstrap.com/">Bootstrap</a> by the team.
          </p>
          <p>
            <a href="#">Back to top</a>
          </p>
        </footer>
      </div>
    </div>
  );
}

export default App;
