import React from 'react'
import item_image from "./assets/startup.jpg"

const MayLikeItem = ({ name, info, business_model}) => {
    return (
        <div className='may-like-item'>
            <img src={item_image} alt='item'/>
            <h2>{name}</h2>
            <p> {info}</p>
            <p>{business_model}</p>
        </div>
    )
}

export default MayLikeItem
