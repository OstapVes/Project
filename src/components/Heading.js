import React from 'react'
import items_image from "./assets/logo.jpg" 

const Heading = () => {
    return (
        <section className='heading'>
            <img className='heading__image' src={items_image} alt='items'/>
            <div className='heading__info'>
                <h2>Startups that are present</h2>
                <p>
                Our website will help you find the startup you need.
                This page contains all startups and information about them. Good luck !!!
                </p>
            </div>    
        </section>
    )
}

export default Heading
