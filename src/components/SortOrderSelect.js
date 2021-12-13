import React, { useState } from 'react'
import Select from 'react-select'

const SortOrderSelect = ({ reverseItems }) => {
    const options = [
        {
          value: 'asc',
          label: 'ASC '
        },
        {
          value: 'desc',
          label: 'DESC'
        }
    ]

    return (
        <Select className='select right'
            placeholder={'ASC'}
            options={options}
            onChange={reverseItems}
        />
    )
}

export default SortOrderSelect
