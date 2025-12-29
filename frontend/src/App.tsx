import { useState } from 'react'
import type { JSX } from 'react'

function App(): JSX.Element {
  const [count, setCount] = useState(0)

  const onButtonPress = (): void => {
    setCount(count + 1);
  }

  return (
    <>
      {count}
      <button onClick={onButtonPress}>Click me</button>
    </>
  )
}

export default App
