import React, { useState } from 'react'
import Select from 'react-select'

const SortParameterSelect = ({ sortItems }) => {
    const options = [
        {
          value: 'single-name',
          label: 'name'
        }
    ]
    
    const [selectedValue, setSelectedValue] = useState('single-name');
    
    const handleValueChange = e => {
        setSelectedValue(e.value);
        sortItems(e.value)
    }

    return (
        <Select className='select left'
            value={options.find(x => x.value === selectedValue)}
            options={options}
            onChange={handleValueChange}
        />
    )
}

export default SortParameterSelect
