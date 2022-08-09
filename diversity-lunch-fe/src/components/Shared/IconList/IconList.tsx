import { FC, Key } from 'react';

export interface IconListComponentProp<T>{item: T}

export interface IconListProps<T>{
  data: T[],
  itemComponent: FC<IconListComponentProp<T>>,
  getKeyFunction: (_:T)=> Key | null | undefined,
}

export const IconList = <T extends unknown>(
  { data, itemComponent, getKeyFunction }: IconListProps<T>,
) => {
  const items = data.map((item: T) => (
    <li key={getKeyFunction(item)}>
      {itemComponent({ item })}
    </li>
  ));

  return (
    <ul>
      {items}
    </ul>
  );
};
