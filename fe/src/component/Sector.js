import React from 'react';

const Sector = ({sectors, temp}) => {
    let count = ""
    return (
        <>
            {sectors.map(m => {
                if (m.parentId != null) {
                    count = temp + "\u00a0\u00a0"
                }

                return (
                    <>
                        <option value={m.id}>{`${count}  ${m.name}`}</option>
                        {m.sectors && <Sector temp={count} sectors={m.sectors}/>}
                    </>
                );
            })}
        </>
    );
}

export default Sector
