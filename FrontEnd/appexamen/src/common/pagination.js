
import React from 'react';

const Pagination = ({ pages, total, paginate, currentPage, previousPage, nextPage }) => {

    const pageNumbers = [];
    const totalOfPages = Math.ceil(total / pages);

    for (let i = 1; i <= totalOfPages; i++) {
        pageNumbers.push(i);
    }

    return (
        <>
            <nav>
                <ul className='pagination'>
                    <li className={`page-item ${1 === currentPage ? 'disabled' : ''}`}>
                        <a className='page-link' onClick={() => previousPage(currentPage)}>Anterior</a>
                    </li>
                    {
                        pageNumbers.map(number => (
                            <li key={number} className={`page-item ${number === currentPage ? 'active' : ''}`} >
                                <a onClick={() => paginate(number)} className='page-link'>
                                    {number}
                                </a>
                            </li>
                        ))
                    }
                    <li className={`page-item ${totalOfPages === currentPage ? 'disabled' : ''}`}>
                        <a className='page-link' onClick={() => nextPage(currentPage)}>Siguiente</a>
                    </li>
                </ul>
            </nav>
        </>
    )
}

export default Pagination;