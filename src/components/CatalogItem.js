import React from 'react'
import { NavLink } from 'react-router-dom'
import item_image from "./assets/startup.jpg"

const CatalogItem = ({id, name, price, type, count }) => {
    return (
        <div className='catalog-item'>
            <div className="item-description">  
                <img className="item-image" src={item_image} alt="Item"/>

                <h3 className="item-name">{name}</h3>
                <label className="item-type">Description: {type}</label>
            </div>
            <div className='btn'> 
            <NavLink to={`/app/${id}`}>Accept the application</NavLink>
            </div>            <NavLink to={`/app/${id}`}>Reject the application</NavLink>

        </div>
    )
}

export default CatalogItem
