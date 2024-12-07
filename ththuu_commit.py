import pandas as pd
df = pd.read_csv('first.csv')
# Read each row
# print(df.head())
# printf(df.tail())
# print(df.iloc[0:2]) in hang 0 vs 1 (not including 2)
# print(df.iloc[3:5, :-1]) // :-1 la tu cot dau den cot ke cuoi
# print(df.iloc[3:5, -1]) // -1 chi in cot cuoi
# print(df["ID", "Name"]) ???
# print(df.loc[(df.Name =='ADC') &(df.Copies == 2),['ID', 'Auther']]) in dong co cot Name = ADC va cot Copies = 2
# df_xlsx = pd.read_excel('')
# for index, row in df.iterrows():
#   print(index, row['2'])

# Read each column
# print(df['ADC'][0:2])
# read a specific location (R,C)
# print(df.iloc[2,0])

# print(df.shape) #(rxc)

# print(df.info())

# print(df.ID.dtype)

# print(df.describe())

# print(df['2'].unique())

# print(list(df.columns))

# print(df.Name.apply(lambda x : x.replace('A', '@'))) chi thay doi khi thuc thi lenh nay
# df.Name = df.Name.apply(lambda x : x.replace('A', '@')) thay doi tren dataset

#tao column moi
# df.new = df.Copies * df.... = df['new'] = df['Copies'] * df['']
# print (df.new.sum())

#most item
#print((df.groupby("Name")['Copies']).sum())

#sorting
#a = df.groupby("Name")['Copies'].sum()
#print(a.sort_values(ascending = False)) sx giam dan

#print(df.Copies.value_counts()) dem so luong tung gia tri cua Copies
