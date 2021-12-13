import Heading from "./Heading"
import MayLikeItem from "./MayLikeItem"
import { useEffect, useState } from "react"
import ScrollToTop from "./ScrollToTop"

const Home = () => {
    let name = "Startup"
    let info = `The company was established recently, 
    building its business on the basis of innovation or 
    innovative technologies, has not entered the market 
    or began to enter it and has limited resources.`
    let business_model=`Business model: Service that helps drivers find 
    passengers and thus reimburse the cost of gasoline, and 
    passengers - to find a driver who can drive him to the right city`

    const [isScrollNeeded, setIsScrollNeeded] = useState(true)

    

    const [mayLikeItems, setItems] = useState([
        {
            id: 0,
            name: name,
            info: info,
            business_model:business_model
        },
        {
            id: 1,
            name: name,
            info: info,
            business_model:business_model

        },
        {
            id: 2,
            name: name,
            info: info,
            business_model:business_model

        },
        {
            id: 3,
            name: name,
            info: info,
            business_model:business_model

        } 
    ])
    const [id, setId] = useState(4)

    const showMoreItems = () => {
        setIsScrollNeeded(false)
        setItems([...mayLikeItems,
            {
                id: id,
                name: name,
                info: info,
                business_model:business_model

            },
            {
                id: id + 1,
                name: name,
                info: info,
                business_model:business_model

            },
            {
                id: id + 2,
                name: name,
                info: info,
                business_model:business_model

            },
            {
                id: id + 3,
                name: name,
                info: info,
                business_model:business_model

            }
        ])
        setId(id + 4)
    }

    return (
        <div className='home'>
            <Heading />
            {isScrollNeeded ? <ScrollToTop /> : null}
            <section className='may-like-items'>
                <h1>You may be interested:</h1>
                <ul>
                    { mayLikeItems.map(item => 
                        <MayLikeItem key={item.id} name={`${item.name} ${item.id + 1}`} info={item.info} business_model={item.business_model}/>) }
                </ul>
                <div className='view-more'>
                    <button className='may-like-items__view-more-button' onClick={showMoreItems}>View more</button>
                </div>    
            </section>
        </div>
    )
}

export default Home
