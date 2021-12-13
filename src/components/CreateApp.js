import React, { useEffect, useState } from 'react'


function CreateApp() {
    return (
        <cart>
            <h1>Create a funding application</h1>
            <h1 class="text-center">Startup</h1> 
            <h3 class="text">Create application</h3>

                <form class="block">
                    <div class="mb-3">
                        <label for="exampleInputEmail1" class="form-label">Name</label>
                        <input type="text" class="form-control"  required />
                    </div>
                    <div class="mb-3">
                        <label for="exampleInput" class="form-label">Description</label>
                        <input type="text" class="form-control"  required />
                    </div>
                    <div class="mb-3">
                        <label for="exampleInput" class="form-label">Website</label>
                        <input type="text" class="form-control"  required />
                    </div>
                    <div class="mb-3">
                        <label for="exampleInput" class="form-label">Amount of funds</label>
                        <input type="text" class="form-control" required />
                    </div>
                    <button class="btn_submit">
                        Submit
                    </button>
                </form>
        </cart>)
}

export default CreateApp
