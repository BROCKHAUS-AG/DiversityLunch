import { FC, Key } from 'react';

export interface GenericListItemComponentProp<T>{item: T}

export interface GenericListProps<T>{
  data: T[],
  itemComponent: FC<GenericListItemComponentProp<T> | any>, // TODO tgohlisch, 29.08.2022: fix typing (don't use any)
  getKeyFunction: (_:T)=> Key | null | undefined,
  itemComponentProps?: {}
}

export const GenericList = <T extends unknown>(
    {
        data, itemComponentProps, itemComponent, getKeyFunction,
    }: GenericListProps<T>,
) => {
    const items = data.map((item: T) => (
        <li key={getKeyFunction(item)}>
            {itemComponent({ item, ...itemComponentProps })}
        </li>
    ));

    return (
        <ul>
            {items}
        </ul>
    );
};
