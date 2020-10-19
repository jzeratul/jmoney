import React from 'react'
import { JMoneyJarButton } from './JMoneyJarButton'

const JMoneyJarButtonsList = props => {

  const onClickHandler = () => {
    props.jarSelectHandler(props.jars)
  };

  return (
    <div>
        {props.jars.map((jar, index) => (
          <JMoneyJarButton
                key={index}
                jar={jar}
                onClickHandler={onClickHandler}
          ></JMoneyJarButton>
        ))}
    </div>
  )
}

export default JMoneyJarButtonsList
