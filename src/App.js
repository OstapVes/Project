import "./App.css"
import { HashRouter, Route } from "react-router-dom"
import Header from "./components/Header"
import Home from "./components/Home"
import Catalog from "./components/Catalog"
import ItemPage from "./components/ItemPage"
import Footer from "./components/Footer"
import { useState } from "react"
import CreateApp  from "./components/CreateApp"

const App = () => {

    const [allItems, setAllItems] = useState([
        {id: 1, name: "Startup Horizon",   type: `The company was established recently, 
        building its business on the basis of innovation or innovative technologies, has not entered 
        the market or began to enter it and has limited resources.
        Service that helps drivers find passengers and thus reimburse the cost of gasoline, and 
        passengers - to find a driver who can drive him to the right city`},
        {id: 2, name: "Startup NNG",   type: `The company was established recently, 
        building its business on the basis of innovation or innovative technologies, has not entered 
        the market or began to enter it and has limited resources.
        Service that helps drivers find passengers and thus reimburse the cost of gasoline, and 
        passengers - to find a driver who can drive him to the right city`, },
        {id: 3, name: "Startup Creative",  type: `The company was established recently, 
        building its business on the basis of innovation or innovative technologies, has not entered 
        the market or began to enter it and has limited resources.
        Service that helps drivers find passengers and thus reimburse the cost of gasoline, and 
        passengers - to find a driver who can drive him to the right city`, },
        {id: 4, name: "Startup Volf",   type: `The company was established recently, 
        building its business on the basis of innovation or innovative technologies, has not entered 
        the market or began to enter it and has limited resources.
        Service that helps drivers find passengers and thus reimburse the cost of gasoline, and 
        passengers - to find a driver who can drive him to the right city`}
    ])

    const [currentlyDisplayedItems, setCurrentlyDisplayedItems] = useState(allItems)

    return (
        <HashRouter>
            <div className='container'>
                <Header />
                <div>
                    <Route exact path='/' component={Home}></Route>
                    <Route path='/catalog' render={props => <Catalog {...props}
                        allItems={allItems} currentlyDisplayedItems={currentlyDisplayedItems}
                        setAllItems={setAllItems} setCurrentlyDisplayedItems={setCurrentlyDisplayedItems}
                        />}></Route>
                    <Route path='/app/:id' render={props => <ItemPage {...props} allItems={allItems}/>}></Route>
                    <Route path='/creating_application' component={CreateApp}></Route>

                </div>
                <Footer />
            </div>
        </HashRouter>
    )
}

export default App
