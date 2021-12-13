import React from 'react'
import { useState } from 'react'
import item_image from "./assets/startup.jpg"

const Item = ({ allItems, id }) => {

    const [item] = useState(allItems.find(item => item.id == id))

    return (
        <div className='item'>
            <img className="item__item-image" src={item_image} alt="Item"/>
            <div className="item-info">
                <h1 className="item-name">{item.name}</h1>
                <h1 className='item-business_model'>{item.business_model}</h1>
                <label className="item-type">Type: {item.type}</label>
                <p>
                 Changes to this object will be made soon!!!!

                </p>
            </div>
        </div>
    )
}

export default Item
