import React from 'react';

function CoasterCard({ coaster }) {
  const { name, design, image } = coaster;
  return (
    <div className="col">
      <div className="card shadow-sm h-100">
        <img
          src={image}
          className="bd-placeholder-img card-img-top"
          alt={name}
          height={350}
        />
        <div className="card-body">
          <h5 className="card-title">{name}</h5>
          <p className="card-text">{design}</p>
        </div>
      </div>
    </div>
  );
}

export default CoasterCard;
